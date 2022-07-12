require 'json'

package = JSON.parse(File.read(File.join(__dir__, 'package.json')))

Pod::Spec.new do |s|
  s.name = 'CapacitorAxaMobileSdkPluginXcframework'
  s.version = package['version']
  s.summary = package['description']
  s.license = package['license']

  s.homepage = package['repository']['url']
  s.author = package['author']
  s.ios.deployment_target  = '12.0'
  s.swift_version = '5.1'

  s.source = { :git => package['repository']['url'], :tag => s.version.to_s }
  s.source_files = 'ios/Plugin/**/*.{swift,h,m,c,cc,mm,cpp}'
  
  s.dependency 'Capacitor'
  s.dependency 'CAMobileAppAnalytics/xcframework'

  s.pod_target_xcconfig = {   'CLANG_ALLOW_NON_MODULAR_INCLUDES_IN_FRAMEWORK_MODULES' => 'YES', 
                                'OTHER_SWIFT_FLAGS' => '-Xcc -Wno-error=non-modular-include-in-framework-module'
                        }
  s.static_framework = true
end
