require 'json'

package = JSON.parse(File.read(File.join(__dir__, 'package.json')))

Pod::Spec.new do |s|
  s.name = 'CapacitorAxaMobileSdkPlugin'
  s.version = package['version']
  s.summary = package['description']
  s.license = package['license']

  s.homepage = package['repository']['url']
  s.author = package['author']
  s.ios.deployment_target = '12.0'
  s.swift_version = '5.1'

  s.source = { :git => package['repository']['url'], :tag => s.version.to_s }
  s.source_files = 'ios/Plugin/**/*.{swift,h,m,c,cc,mm,cpp}'
  
  s.dependency 'Capacitor'
  s.dependency 'CAMobileAppAnalytics', '~> 25.8.2'
  s.script_phases = [
      {   :name => 'Precompile',
         :script => 'cd "${PODS_ROOT}"
                    irb <<EOF
                    require "xcodeproj"
                    require "shellwords"

project_path = "Pods.xcodeproj/"
project_path = "#{project_path}".shellescape
project = Xcodeproj::Project.open(project_path)
project.targets.each do |target|
    if target.name == "CapacitorAxaMobileSdkPlugin" && !(target.headers_build_phase.file_display_names.include? "CAMDOReporter.h")
        file_ref = project.files.select { |project_file| project_file.display_name == "CAMDOReporter.h" }[0]
        header_file = target.headers_build_phase.add_file_reference (file_ref)
        header_file.settings = { "ATTRIBUTES" => ["Public"] }
        puts "Done the updatation"
        project.save
    end
end
                    EOF
                    cd - ',
         :execution_position => :before_compile
       }
  ]
  s.pod_target_xcconfig = {   'CLANG_ALLOW_NON_MODULAR_INCLUDES_IN_FRAMEWORK_MODULES' => 'YES', 
                                'OTHER_SWIFT_FLAGS' => '-Xcc -Wno-error=non-modular-include-in-framework-module'
                        }
  s.static_framework = true
end
